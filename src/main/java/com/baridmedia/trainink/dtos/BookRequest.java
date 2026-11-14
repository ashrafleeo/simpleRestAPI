package com.baridmedia.trainink.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(@NotBlank String title, @NotNull Long authorId) {}
