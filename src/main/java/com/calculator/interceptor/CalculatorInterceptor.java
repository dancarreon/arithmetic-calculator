package com.calculator.interceptor;

import com.calculator.model.Record;
import com.calculator.model.SecurityUser;
import com.calculator.model.Type;
import com.calculator.model.User;
import com.calculator.model.dto.Message;
import com.calculator.model.dto.OperationRecord;
import com.calculator.model.dto.RecordRecord;
import com.calculator.model.dto.UserRecord;
import com.calculator.service.OperationService;
import com.calculator.service.RecordService;
import com.calculator.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

/*
 * Intercepts all Requests and Responses done to the Calculator in order to:
 * - Verifies if the User has authenticated
 * - Estimates if the User has balance to afford the cost of the operation
 * - Logs the operation (if it's successful) and creates a new Record entry
 * */
@AllArgsConstructor
@Log4j2
public class CalculatorInterceptor implements HandlerInterceptor {

    private final UserService userService;
    private final RecordService recordService;
    private final OperationService operationService;
    private final Environment environment;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        if (getAuthenticatedUser() != null) {

            List<RecordRecord> userRecords = userService.getUserRecords();
            OperationRecord operationRecord = operationService.getOperationByType(getType(request.getRequestURI()));

            if (userRecords.isEmpty()) {
                // if User has no Record history, return true, all Users have a DEFAULT_USER_BALANCE of 10
                return true;
            } else {
                // verify the first record (last, since it's in descendent order by date) and checks if user balance can afford the operation cost
                if (userRecords.getFirst().userBalance() >= operationRecord.cost()) {
                    return true;
                } else {
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write(mapper.writeValueAsString(new Message("User's balance is insufficient")));
                }
            }
        } else {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(mapper.writeValueAsString(new Message("User not authorized")));
        }

        return false;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, ModelAndView modelAndView) throws Exception {

        if (getAuthenticatedUser() != null) {
            if (response.getStatus() == HttpStatus.OK.value()) {
                List<RecordRecord> userRecords = userService.getUserRecords();
                UserRecord userRecord = userService.getAuthenticatedUser();
                OperationRecord operationRecord = operationService.getOperationByType(getType(request.getRequestURI()));

                Record record = getRecord(operationRecord, userRecord, userRecords, response);

                recordService.addRecord(record);
            }
        }
    }

    private Record getRecord(OperationRecord operationRecord, UserRecord userRecord, List<RecordRecord> userRecords, HttpServletResponse response) {

        Record record = new Record();
        record.setOperation(operationRecord.toOperation());
        record.setUser(userRecord.toUser());
        record.setAmount(operationRecord.cost());
        record.setDate(Instant.now());
        record.setOperationResponse(response.getStatus());

        if (userRecords.isEmpty()) {
            int defaultUserBalance = Integer.parseInt(Objects.requireNonNull(environment.getProperty("user.default.balance")));
            record.setUserBalance(defaultUserBalance - operationRecord.cost());
        } else {
            record.setUserBalance(userRecords.getFirst().userBalance() - operationRecord.cost());
        }

        return record;
    }

    public Object getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getPrincipal();
        }
        return null;
    }

    // Returns the Operation Type from the URI
    private Type getType(String URIOperation) {
        for (Type t : Type.values()) {
            if (URIOperation.toLowerCase().contains(t.name().replace("_", "-").toLowerCase())) {
                return t;
            }
        }
        return null;
    }
}
