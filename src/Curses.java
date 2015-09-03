
public interface Curses {
     public static final String ANSI_RESET = "\u001B[0m";
     public static final String ANSI_CURSOR_UP = "\u001B[1A";
     public static final String ANSI_CURSOR_DOWN = "\u001B[1B";
     public static final String ANSI_CURSOR_FORWARD = "\u001B[1C";
     public static final String ANSI_CURSOR_BACK = "\u001B[1D";
     public static final String ANSI_CURSOR_NEXT_LINE = "\u001B[1E";
     public static final String ANSI_CURSOR_PREV_LINE = "\u001B[1F";
     public static final String ANSI_CURSOR_MOVE_TO = "\u001B[3;3H";
     public static final String ANSI_CURSOR_SAVE_POS = "\u001B[s";
     public static final String ANSI_CURSOR_RESTORE_POS = "\u001B[u";
     public static final String ANSI_CURSOR_SHOW = "\u001B[?25h";
     public static final String ANSI_CURSOR_HIDE = "\u001B[?25l";

     /**
      * Réinitialise la console (utile lorsque vous jouez avec les
      * couleurs du texte !). Il est de bon ton d'appeler cette fonction
      * avant de quitter votre programme afin de ne pas modifier la
      * console de l'utilisateur ...
      */
     public void reset();

     /**
      * Affiche le curseur.
      */
     public void show();
     /**
      * Masque le curseur.
      */
     public void hide();

     public static final char ANSI_UP    = 17;
     public static final char ANSI_DOWN  = 18;
     public static final char ANSI_LEFT  = 19;
     public static final char ANSI_RIGHT = 20;

     /**
      * Déplace le curseur vers le haut.
      */
     public void up();
     /**
      * Déplace le curseur de n lignes vers le haut.
      *
      * @param n le nombre de lignes.
      */
     public void up(int n);
     /**
      * Déplace le curseur vers le haut.
      */
     public void down();
     /**
      * Déplace le curseur de n lignes vers le bas.
      *
      * @param n le nombre de lignes.
      */
     public void down(int n);
     /**
      * Déplace le curseur vers la droite.
      */
     public void forward();
     /**
      * Déplace le curseur de n cases vers la droite.
      *
      * @param n nombres de cases vers la droite.
      */
     public void forward(int n);
     /**
      * Déplace le curseur vers la gauche.
      */
     public void backward();
     /**
      * Déplace le curseur de n cases vers la gauche.
      *
      * @param n nombre de cases vers la gauche.
      */
     public void backward(int n);

     /**
      * Sauvegarde la position courante du curseur (CUrsor Save Position).
      */
     public void cusp();
     /**
      * Restaure la position sauvegardée du curseur (CUrsor Restore
Position).
      */
     public void curp();
     /**
      * Positionne le curseur aux coordonnées (line, column).
      *
      * @param line la coordonnée de ligne où positionner le curseur.
      * @param column la coordonnée de colonne où positionner le curseur.
      */
     public void cursor(int line, int column);

     public static final String ANSI_CLEAR_SCREEN_AFTER = "\u001B[0J";
     public static final String ANSI_CLEAR_SCREEN_BEFORE = "\u001B[1J";
     public static final String ANSI_CLEAR_SCREEN_ALL = "\u001B[2J";
     public static final String ANSI_CLEAR_LINE_AFTER = "\u001B[0K";
     public static final String ANSI_CLEAR_LINE_BEFORE = "\u001B[1K";
     public static final String ANSI_CLEAR_LINE_ALL = "\u001B[2K";

     /**
      * Efface la ligne depuis la position jusqu'à la fin de la ligne.
      */
     public void clearEOL();
     /**
      * Efface la ligne depuis le début de la ligne jusqu'au curseur.
      */
     public void clearBOL();
     /**
      * Efface l'ensemble du contenu de la ligne courante.
      */
     public void clearLine();
     /**
      * Efface l'ensemble de l'écran et repositionne le curseur en (1, 1).
      */
     public void clearScreen();

     public static final String ANSI_SCROLL_UP = "\u001B[1S";
     public static final String ANSI_SCROLL_DOWN = "\u001B[1T";
     public static final String ANSI_BOLD = "\u001B[1m";
     public static final String ANSI_FAINT = "\u001B[2m";
     public static final String ANSI_ITALIC = "\u001B[3m";
     public static final String ANSI_UNDERLINE = "\u001B[4m";
     public static final String ANSI_BLINK_SLOW = "\u001B[5m";
     public static final String ANSI_BLINK_FAST = "\u001B[6m";
     public static final String ANSI_NEGATIVE = "\u001B[7m";
     public static final String ANSI_BLACK = "\u001B[30m";
     public static final String ANSI_RED = "\u001B[31m";
     public static final String ANSI_GREEN = "\u001B[32m";
     public static final String ANSI_YELLOW = "\u001B[33m";
     public static final String ANSI_BLUE = "\u001B[34m";
     public static final String ANSI_PURPLE = "\u001B[35m";
     public static final String ANSI_CYAN = "\u001B[36m";
     public static final String ANSI_WHITE = "\u001B[37m";

     /**
      * Défini la couleur de l'affichage du texte.
      *
      * @param color une des couleurs définies dans le tableau ANSI_COLORS.
      */
     public void text(String color);

     public static String[] ANSI_COLORS = new String[]{"black", "red",
"green",
         "yellow", "blue", "purple", "cyan", "white"};

     /**
      * Cette fonction retourne aléatoirement une chaîne de caractère
      * représentant une couleur (parmi les couleurs définies dans le
      * tableau ANSI_COLORS).
      *
      * @return une couleur tirée aléatoirement parmi les couleurs
définies dans
      * le tableau ANSI_COLORS
      */
     public String randomANSIColor();

     /**
      * Défini la couleur de l'affichage du fond du texte.
      *
      * @param color une des couleurs définies dans le tableau ANSI_COLORS.
      */
     public void background(String color);

     public static final String ANSI_TEXT_DEFAULT_COLOR = "\u001B[39m";
     public static final String ANSI_BLACK_BG = "\u001B[40m";
     public static final String ANSI_RED_BG = "\u001B[41m";
     public static final String ANSI_GREEN_BG = "\u001B[42m";
     public static final String ANSI_YELLOW_BG = "\u001B[43m";
     public static final String ANSI_BLUE_BG = "\u001B[44m";
     public static final String ANSI_PURPLE_BG = "\u001B[45m";
     public static final String ANSI_CYAN_BG = "\u001B[46m";
     public static final String ANSI_WHITE_BG = "\u001B[47m";
     public static final String ANSI_BG_DEFAULT_COLOR = "\u001B[49m";

     // Key event management in console mode
     /**
      * Active (true) ou désactive (false) la possibilité de capturer les
      * touches manipulées par l'utilisateur. Pour être notifié des touches
      * manipulées, il faut écrire la fonction 'void
keyTypedInConsole(char c)'.
      * Dès que l'utilisateur appuiera sur une touche, la méthode
'keyTypedInConsole'
      * sera automatiquement appelée avec en paramètre la valeur de la
touche
      * manipulée par l'utilisateur. Les constantes ANSI_UP, ANSI_DOWN,
ANSI_LEFT
      * et ANSI_RIGHT permettent d'identifier les touches directionnelles.
      *
      * @param on activation de la capture (true) ou désactivation (false).
      */
     public void enableKeyTypedInConsole(boolean on);
}
