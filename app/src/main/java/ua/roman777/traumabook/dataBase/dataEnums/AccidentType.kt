package ua.roman777.traumabook.dataBase.dataEnums


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
enum class AccidentType(var acName: String) {
    ROAD_ACCIDENT("ДТП"),
    ALTITUDE_ACCIDENT("Падіння з висоти"),
    DOMESTIC_INJURY("Побутова травма"),
    WAR_SHELL("Вогнепальне поранення"),
    WAR_MINE("Мінно-вибухова травма"),
    WAR_WAVE("Дія вибухової хвилі"),
    UNDEFINED("Невизначено")
}