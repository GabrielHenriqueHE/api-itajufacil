package io.github.gabrielhenriquehe.itajufacil.dto;

public class ApiResponse<T> {

    public Integer status;
    public String message;
    public T data;

    public ApiResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(Integer status, String message, T data) {
        this(status, message);
        this.data = data;
    }

}
