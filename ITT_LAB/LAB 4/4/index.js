function validateForm() {
    var usn = document.forms["usnForm"]["usn"].value;
    var semester = document.forms["usnForm"]["semester"].value;
    var usnRegex = /^[1-4][A-Z]{2}\d{2}[A-Z]{2}\d{3}$/;

    if (!usnRegex.test(usn)) {
        alert("Invalid USN. Please enter a valid USN.");
        return false;
    }

    if (semester < 1 || semester > 8) {
        alert("Invalid semester. Please enter a semester between 1 and 8.");
        return false;
    }

    alert("Form submitted successfully!");
    return true;
}
