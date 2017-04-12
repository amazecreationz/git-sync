package com.amazecreationz.gitsync.services;

import com.amazecreationz.gitsync.constants.MessageConstants;

import java.util.HashMap;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 4:12 PM.
 */
public class MessageService implements MessageConstants {
    private String replaceWithParams(String message, HashMap<String, String> messageParams) {
        for(String messageParamName : messageParams.keySet()) {
            String paramPattern = "\\$\\{("+messageParamName+")\\}";
            message = message.replaceAll(paramPattern, messageParams.get(messageParamName));
        }
        return message;
    }

    private void displayMessage(String message, HashMap<String, String> messageParams) {
        message = replaceWithParams(message, messageParams);
        System.out.println(message);
    }

    public void displayMessageFromErrorCode(int errorCode, HashMap<String, String> messageParams) {
        String message;
        switch (errorCode) {
            case NEW_PROJECT_SUCCESS: message = PROJECT_SUCCESS_MESSAGE;
                break;
            case ERR_DUPLICATE: message = ERR_DUPLICATE_PROJECT_MESSAGE;
                break;
            case ERR_NOT_FOUND: message = ERR_NO_PROJECT_FOUND_MESSAGE;
                break;
            case PROJECT_DELETE_SUCCESS: message = PROJECT_DELETE_SUCCESS_MESSAGE;
                break;
            case PROJECT_DELETE_FAILURE: message = PROJECT_DELETE_FAILURE_MESSAGE;
                break;
            default: message = NO_CODES_ASSOCIATED;
        }
        displayMessage(message, messageParams);
    }

    public void displayMessageFromErrorCode(int errorCode) {
        displayMessageFromErrorCode(errorCode, null);
    }
}
