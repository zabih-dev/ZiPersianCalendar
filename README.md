# ZiPersianCalendar

A simple and flexible **Persian Calendar** library for **Android**
that helps developers easily manage and convert **Persian dates**.

## Features

- No dependencies
- Supports lower SDK levels
- Easily set dates, manage time fields, and add calendar fields
- Handles leap years in the Persian calendar system
- Simple API to work with **Persian dates (Jalali / Shamsi dates)**

## Setup

To add this library to your project, follow the instructions below.

### Step 1: Add JitPack to Your Project

Add the JitPack repository to your root `build.gradle` or `settings.gradle.kts` at the end of repositories:

#### Kotlin Script (settings.gradle.kts)

```gradle
dependencyResolutionManagement {
    ...
    repositories {
        google()
        mavenCentral()
        // Add this
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
```

#### Groovy (build.gradle):

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the Dependency

Add the following dependency to your app's `build.gradle` file:

#### Kotlin Script (build.gradle.kts)

```gradle
dependencies {
    ...
    implementation("com.github.zabih-dev:ZiPersianCalendar:1.0.0")
}
```

#### Groovy (build.gradle):

```gradle
dependencies {
    ...
    implementation 'com.github.zabih-dev:ZiPersianCalendar:1.0.0'
}
```

## Usage

### Initialize the Persian Calendar

You can initialize the Persian calendar by simply creating an instance of the `ZiPersianCalendar` class.

#### Example:

```java
// Today's calendar
ZiPersianCalendar calendar = new ZiPersianCalendar();
```

### Set a Specific Date

You can set a date by specifying the year, month, and day.

#### Example:

```java
// Set a specific date e.g. 1403-06-01
ZiPersianCalendar calendar = new ZiPersianCalendar(1403, ZiPersianCalendar.MONTH_SHAHRIVAR, 1)

// Or set a specific date after init
ZiPersianCalendar calendar2 = new ZiPersianCalendar();
calendar2.set(1403, ZiPersianCalendar.MONTH_SHAHRIVAR, 1)
```

### Get the Formatted Date

```java
// 1403-06-01

// returns: پنج‌شنبه 01 شهریور 1403
calendar.getLongDate();

// returns: 1403/06/01
calendar.getShortDate();

// returns: 1403-06-01
calendar.getShortDate("-");
```

### Add a Field to the Date

You can add time fields such as days, months, or years to the current date.

#### Example:

```java
calendar.add(Calendar.DAY_OF_MONTH, 7); // Add 7 days to the current date
calendar.add(Calendar.MONTH, 1); // Add 1 month to the current date
calendar.add(Calendar.YEAR, 1); // Add 1 year to the current date
```

### Customizing Calendar Fields

If you need to modify or add new fields, you can do so by using the calendar's internal field methods.

#### Example:

```java
calendar.set(Calendar.HOUR_OF_DAY, 10); // Set the hour of the day
calendar.set(Calendar.MINUTE, 30); // Set the minutes
calendar.set(Calendar.SECOND, 0); // Set the seconds
```

### Handling Leap Years

To check if the current year is a leap year:

```java
boolean isLeap = calendar.isLeapYear();
System.out.println("Is leap year: " + isLeap);
```

## License

This library is available under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0). See the `LICENSE` file for more details.

    Copyright 2013 Zabih-Dev, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

---
