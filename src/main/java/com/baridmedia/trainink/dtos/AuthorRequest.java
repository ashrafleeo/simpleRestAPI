package com.baridmedia.trainink.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequest(@NotBlank String name) {}
