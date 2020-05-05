package org.tm.common.generator;

import org.tm.common.generator.generator.ModelGenerator;

/*
    author: Timor
    date: 2020/3/8 0008
*/
public class GeneratorFactory {
    public ModelGenerator createModelGenerator(){
        return new ModelGenerator(null);
    }

}
