package io.whitesource.cure;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class SecureObjectInputStream extends ObjectInputStream {

    private final Set<String> classesWhitelist;
    private final Set<String> packagesWhitelist = new HashSet<>();

    public SecureObjectInputStream(InputStream inputStream, Set<String> validClassNames) throws IOException {
        super(inputStream);
        this.classesWhitelist = validClassNames;
    }

    public SecureObjectInputStream(InputStream inputStream, Set<String> validClassNames, Set<String> validPackages) throws IOException {
        super(inputStream);
        this.classesWhitelist = validClassNames;
        this.packagesWhitelist.addAll(validPackages);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String className = desc.getName();
        String packageName = StringUtils.substringBeforeLast(className, ".");
        if (classesWhitelist.contains(className) || packagesWhitelist.contains(packageName)) {
            return super.resolveClass(desc);
        }
        throw new InvalidClassException("Unauthorized deserialization attempt detected for class " + desc.getName());
    }
}
