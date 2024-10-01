package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class ServerRequest implements Serializable
{
    private String requestType;

    public ServerRequest(String requestType)
    {
        this.requestType = requestType;
    }

    public String getRequestType()
    {
        return requestType;
    }
}
