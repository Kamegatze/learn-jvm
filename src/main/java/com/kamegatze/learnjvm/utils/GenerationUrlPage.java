package com.kamegatze.learnjvm.utils;

import com.kamegatze.learnjvm.model.generation.url.Parameters;

import java.util.List;

public interface GenerationUrlPage {

    List<String> generation(final Parameters parameters);

}
