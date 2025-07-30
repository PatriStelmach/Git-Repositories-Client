package com.example.demo.Config;

import com.example.demo.Errors.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientErrorDecoder implements ErrorDecoder
{
    //custom error when retrieving 404 user not found from GitHub API
    @Override
    public Exception decode(String methodKey, Response response)
    {
        if(response.status() == 404)
        {
            throw new UserNotFoundException("User with given username not found");
        }
            return new ErrorDecoder.Default().decode(methodKey, response);
        }
    }

