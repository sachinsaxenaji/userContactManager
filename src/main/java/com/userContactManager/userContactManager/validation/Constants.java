package com.userContactManager.userContactManager.validation;

interface Constants {
    static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+";
    static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

    static final String PATTERN =
            "^" + ATOM + "+(\\." + ATOM + "+)*@"
                    + DOMAIN
                    + "|"
                    + IP_DOMAIN
                    + ")$";
}
