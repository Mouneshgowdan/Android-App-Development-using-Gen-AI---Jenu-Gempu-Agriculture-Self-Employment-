package com.jenugumpu.domain.model

enum class HoneyGrade(
    val labelKn: String,
    val icon: String,
    val descriptionKn: String
) {
    A("ಉತ್ತಮ", "A", "ಕಡಿಮೆ ತೇವಾಂಶ, ಸ್ವಚ್ಛ ಬಣ್ಣ"),
    B("ಮಧ್ಯಮ", "B", "ಸಾಮಾನ್ಯ ಗುಣಮಟ್ಟ"),
    C("ಕಡಿಮೆ", "C", "ಹೆಚ್ಚು ತೇವಾಂಶ ಅಥವಾ ಗಾಢ ಬಣ್ಣ")
}
