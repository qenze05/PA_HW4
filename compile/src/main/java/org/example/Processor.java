package org.example;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.util.Set;
import java.io.*;

@SupportedAnnotationTypes({
        "MinLength",
        "ContainsSpecialChar",
        "ContainsDigit",
        "ContainsUpperLowerCase"
})
public class Processor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(MinLength.class)) {
            MinLength minLength = element.getAnnotation(MinLength.class);
            generateValidatorClass("MinLengthValidator", minLength.value());
        }
        // Інші перевірки аналогічні
        return true;
    }

    private void generateValidatorClass(String className, int minLength) {
        try {
            JavaFileObject file = processingEnv.getFiler().createSourceFile("generated." + className);
            try (Writer writer = file.openWriter()) {
                writer.write("package generated;\n");
                writer.write("public class " + className + " {\n");
                writer.write("    public boolean isValid(String password) {\n");
                writer.write("        return password.length() > " + minLength + ";\n");
                writer.write("    }\n");
                writer.write("}\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
