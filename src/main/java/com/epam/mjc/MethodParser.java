package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all its members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with the following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        // Разделяем строку на две части: до скобок и внутри скобок
        String[] parts = signatureString.split("\\(");
        String beforeArgs = parts[0].trim(); // Часть до скобок
        String argsPart = parts[1].replace(")", "").trim(); // Часть внутри скобок

        // Разбираем часть до скобок
        String[] beforeArgsParts = beforeArgs.split(" ");
        String accessModifier = null;
        String returnType;
        String methodName;

        if (beforeArgsParts.length == 3) {
            accessModifier = beforeArgsParts[0];
            returnType = beforeArgsParts[1];
            methodName = beforeArgsParts[2];
        } else {
            returnType = beforeArgsParts[0];
            methodName = beforeArgsParts[1];
        }

        // Разбираем аргументы
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (!argsPart.isEmpty()) {
            String[] args = argsPart.split(",");
            for (String arg : args) {
                String[] argParts = arg.trim().split(" ");
                String argType = argParts[0];
                String argName = argParts[1];
                arguments.add(new MethodSignature.Argument(argType, argName));
            }
        }

        // Создаем объект MethodSignature
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
