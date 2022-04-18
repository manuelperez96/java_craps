package message;

public abstract class Messages {
    public static String welcome = "¡Bienvenidos al mejor casino del mundo!\n¿¡Estás listo para ganar una gran cantidad de dinero jugando a Scraps!?";
    public static String initialGameOptions = "¿Qué quieres hacer?\n1.- Jugar.\n2.- Salir.";
    public static String incorrectInputOption = "No has introducido una opción correcta";
    public static String goodBye = "Nos vemos pronto. Hasta luego.";
    public static String playerType = "¿Qué tipo de jugadores eres?\n1.- Base.\n2.- Rico.";
    public static String whatIsYourName = "¿Cómo te llamas?";

    public static String areYouSureOfYourName(String name) {
        return String.format("Me confiarmas que te llamas: %s\n1.- Sí, me llamo %s.\n2.- Me he equivocado al decírtelo.", name, name);
    }

    public static String insufficientMoney = "Vaya, parece que te has quedado sin dinero. Vuelve cuando tengas más.";

    public static String currentMoney(double money) {
        return "Dinero actual:" + money;
    }

    public static String betAmount = "¿Cuánto quiere apostar?";

    public static String currentMinBet(double money) {
        return "Apuesta mínima actual: " + money;
    }

    public static String minBetShouldBe(double money) {
        return "La apuesta tiene que ser como mínimo " + money;
    }

    public static String maxBetShouldBe = "No tienes tanto dinero para apostar";

    public static String invalidValue = "Valor inválido";

    public static String showDiceMessage(boolean isFull) {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("¿Qué quieres hacer?\n");
        stringBuilder.append("1. Lanzar dados");
        if (isFull) {
            stringBuilder.append("\n2. Apostar");
        }

        return stringBuilder.toString();
    }

    public static String showDiceThrow(int value) {
        return "Has obtenido un " + value;
    }

    public static String congratulations = "Enhorabuena, has ganado.";
    public static String endGame = "Más suerte la próxima vez, has perdido.";
    public static String throwAgain = "Parece que volverás a tirar.";
}
