package com.mrkt.admin.dto.response;

import com.mrkt.admin.enums.Result;

public class CreateResponse extends Response {

    public static CreateResponse Builder(){return new CreateResponse();}

    @Override
    public CreateResponse build(Result result)
    {
        setResult(result);
        setDescription(result.getDescription());
        return this;
    }

    @Override
    public CreateResponse result(Result result){
        setResult(result);
        return this;
    }

    @Override
    public CreateResponse data(Object data)
    {
        setData(data);
        return this;
    }

    @Override
    public CreateResponseData getData(){return (CreateResponseData) super.getData();}
    public void setData(CreateResponseData data){super.setData(data);}

}
