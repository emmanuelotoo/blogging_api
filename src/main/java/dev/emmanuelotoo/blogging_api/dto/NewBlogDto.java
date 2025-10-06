package dev.emmanuelotoo.blogging_api.dto;

import java.util.ArrayList;

public record NewBlogDto(
        String title,
        String content,
        String category,
        ArrayList<String> tags
) { }
