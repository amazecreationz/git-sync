package com.amazecreationz.gitsync.constants;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 4:13 PM.
 */
public interface MessageConstants extends GlobalConstants{
    /*Error Codes*/
    int FAILURE = 0;
    int NEW_PROJECT_SUCCESS = 1;
    int ERR_DUPLICATE = 2;
    int ERR_NOT_FOUND = 3;
    int PROJECT_DELETE_SUCCESS = 4;
    int PROJECT_DELETE_FAILURE = 5;
    /*Error Codes*/

    /*Error Messages*/
    String NO_CODES_ASSOCIATED = "Some error occured!";
    String PROJECT_SUCCESS_MESSAGE = "New project - \'${"+STRING_PROJECT_NAME+"}\' has been created successfully at ${"+STRING_TIME+"}.";
    String PROJECT_DELETE_SUCCESS_MESSAGE = "Project - \'${" + STRING_PROJECT_NAME + "}\' has been deleted successfully.";
    String PROJECT_DELETE_FAILURE_MESSAGE = "Project - \'${" + STRING_PROJECT_NAME + "}\' could not be deleted. :/";
    String ERR_DUPLICATE_PROJECT_MESSAGE = "A project with same name exists. Try a different name.";
    String ERR_NO_PROJECT_FOUND_MESSAGE = "No project with name \'${"+STRING_PROJECT_NAME+"}\' exists.";
    /*Error Messages*/
}
