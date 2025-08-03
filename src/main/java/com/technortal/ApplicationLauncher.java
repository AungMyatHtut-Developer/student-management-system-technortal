package com.technortal;

import com.technortal.studentmanagementsystemtechnortal.StudentManagementSystem;

public interface ApplicationLauncher {

    static void main(String... args) {
        new StudentManagementSystem(args);
    }
}
