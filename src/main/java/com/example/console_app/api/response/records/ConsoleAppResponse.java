package com.example.console_app.api.response.records;

import com.example.console_app.api.response.interfaces.Response;

public record ConsoleAppResponse(boolean to_input, String message) implements Response {
}
