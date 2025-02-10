package com.mrkt.admin.dto.response;

import com.mrkt.admin.enums.Result;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.util.Objects;


public class ResponseBase {

    @NotNull
    private Result result;

    @NotNull
    private String description;

    public static ResponseBase Builder(){
        return new ResponseBase();
    }

    public ResponseBase result(Result result)
    {
        this.result=result;
        return this;
    }
    public ResponseBase build(Result result)
    {
        this.result=result;
        this.description=result.getDescription();
        return this;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setDescription(String description) {this.description = description;}

    public String getDescription() { return description;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseBase responseSuccess = (ResponseBase) o;
        return Objects.equals(this.result,responseSuccess.result) &&
                Objects.equals(this.description,responseSuccess.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, description);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
