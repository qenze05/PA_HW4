package org.example;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes({
        "MinLength",
        "ContainsSpecialChar",
        "ContainsDigit",
        "ContainsUpperLowerCase"
})
public class Processor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Filer filer = processingEnv.getFiler();
        Messager messager = processingEnv.getMessager();

        for (Element element : roundEnv.getElementsAnnotatedWith(MinLength.class)) {
            MinLength minLength = element.getAnnotation(MinLength.class);
            generateMinLengthValidatorClass(minLength.value(), filer, messager);
        }

        for (Element ignored : roundEnv.getElementsAnnotatedWith(ContainsSpecialChar.class)) {
            generateContainsSpecialCharValidatorClass(filer, messager);
        }

        for (Element ignored : roundEnv.getElementsAnnotatedWith(ContainsDigit.class)) {
            generateContainsDigitValidatorClass(filer, messager);
        }

        for (Element ignored : roundEnv.getElementsAnnotatedWith(ContainsUpperLowerCase.class)) {
            generateContainsUpperLowerCaseValidatorClass(filer, messager);
        }

        return true;
    }

    private void generateMinLengthValidatorClass(int minLength, Filer filer, Messager messager) {
        String className = "MinLengthValidator";
        try {
            JavaFileObject file = filer.createSourceFile("generated." + className);
            try (Writer writer = file.openWriter()) {
                writer.write("package generated;\n");
                writer.write("public class " + className + " {\n");
                writer.write("    public boolean isValid(String password) {\n");
                writer.write("        return password.length() > " + minLength + ";\n");
                writer.write("    }\n");
                writer.write("}\n");
            }
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.toString());
        }
    }

    private void generateContainsSpecialCharValidatorClass(Filer filer, Messager messager) {
        String className = "ContainsSpecialCharValidator";
        try {
            JavaFileObject file = filer.createSourceFile("generated." + className);
            try (Writer writer = file.openWriter()) {
                writer.write("package generated;\n");
                writer.write("public class " + className + " {\n");
                writer.write("    public boolean isValid(String password) {\n");
                writer.write("        return password.matches(\".*[!@#$%^&*()_+\\\\-=\\\\[\\\\]{};':\\\\\"\\\\|,.<>\\\\/?].*\");\n");
                writer.write("    }\n");
                writer.write("}\n");
            }
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.toString());
        }
    }

    private void generateContainsDigitValidatorClass(Filer filer, Messager messager) {
        String className = "ContainsDigitValidator";
        try {
            JavaFileObject file = filer.createSourceFile("generated." + className);
            try (Writer writer = file.openWriter()) {
                writer.write("package generated;\n");
                writer.write("public class " + className + " {\n");
                writer.write("    public boolean isValid(String password) {\n");
                writer.write("        return password.matches(\".*\\\\d.*\");\n");
                writer.write("    }\n");
                writer.write("}\n");
            }
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.toString());
        }
    }

    private void generateContainsUpperLowerCaseValidatorClass(Filer filer, Messager messager) {
        String className = "ContainsUpperLowerCaseValidator";
        try {
            JavaFileObject file = filer.createSourceFile("generated." + className);
            try (Writer writer = file.openWriter()) {
                writer.write("package generated;\n");
                writer.write("public class " + className + " {\n");
                writer.write("    public boolean isValid(String password) {\n");
                writer.write("        return password.matches(\".*[A-Z].*\") && password.matches(\".*[a-z].*\");\n");
                writer.write("    }\n");
                writer.write("}\n");
            }
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.toString());
        }
    }
}
