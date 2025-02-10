package com.mrkt.admin.dto.response;

import com.mrkt.admin.enums.Result;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Response extends ResponseBase {

    @ApiModelProperty(required = true, notes = "Data created by the request")
    private Object data;

    public static Response Builder(){
        return new Response();
    }

    public Response build(Result result)
    {
        setResult(result);
        setDescription(result.getDescription());
        return this;
    }

    public void setResult(Result result) {
        setResult(result);
    }

    public Response result(Result result){
        setResult(result);
        return this;
    }
    public Response description(String description){
        setDescription(description);
        return this;
    }
    public Response data(Object data)
    {
        this.data=data;
        return this;
    }

    @ApiModelProperty(value = "")
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return data.equals(response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
