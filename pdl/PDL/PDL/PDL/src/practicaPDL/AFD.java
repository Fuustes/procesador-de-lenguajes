package practicaPDL;


public class AFD {

	
	private final static int NUM_ESTADOS=11;
	private final static int NUM_ENTRADAS=24;
	private static EstadoAccion[][] matriz=new EstadoAccion[NUM_ESTADOS][NUM_ENTRADAS];

	public AFD() {
		matriz=new EstadoAccion[NUM_ESTADOS][NUM_ENTRADAS];
		inicializarMatriz();
	}

	public static void inicializarMatriz() {
		for (int i=0; i<NUM_ESTADOS; i++) {
			for (int j=0; j<NUM_ENTRADAS; j++) {
				matriz[i][j] = new EstadoAccion(null, "69"); 
			}
		}

		//Estado 0
		matriz[0][0] = new EstadoAccion(0, "L");         // del, estado 0, accion L 
		matriz[0][1] = new EstadoAccion(12, "G17");      // eof, estado 12, accion G17 
		matriz[0][2] = new EstadoAccion(1, "L");         // &, estado 1, accion L 
		matriz[0][3] = new EstadoAccion(2, "L");         // * , estado 2, accion L
		matriz[0][4] = new EstadoAccion(21, "G9");       // =, estado 21, accion G9
		matriz[0][5] = new EstadoAccion(3, "C");         // ", estado 3, accion C
		matriz[0][6] = new EstadoAccion(4, "N");         // digito , estado 4, accion N
		matriz[0][7] = new EstadoAccion(null, "50");     // ., ERROR 50
		matriz[0][8] = new EstadoAccion(7, "C1");        // _ , estado 7, accion C1
		matriz[0][9] = new EstadoAccion(8, "C1");        // letra , estado 8, accion C1
		matriz[0][10] = new EstadoAccion(9, "L");        // /, estado 9, accion L 
		matriz[0][11] = new EstadoAccion(13, "G1");      // ,, estado 13, accion G1
		matriz[0][12] = new EstadoAccion(14, "G2");      // ;, estado 14, accion G2
		matriz[0][13] = new EstadoAccion(15, "G3");      // :, estado 15, accion G3 
		matriz[0][14] = new EstadoAccion(16, "G4");      // (, estado 16, accion G4
		matriz[0][15] = new EstadoAccion(17, "G5");      // ), estado 17, accion G5
		matriz[0][16] = new EstadoAccion(18, "G6");      // {, estado 18, accion G6
		matriz[0][17] = new EstadoAccion(19, "G7");      // }, estado 19, accion G7
		matriz[0][18] = new EstadoAccion(20, "G8");      // +, estado 20, accion G8
		matriz[0][19] = new EstadoAccion(29, "G12");     // >, estado 29, accion G12
		matriz[0][20] = new EstadoAccion(null, "51");    // o.c, ERROR 51 
		matriz[0][21] = new EstadoAccion(0, "L");    // \n, ERROR 52 
		matriz[0][22] = new EstadoAccion(0, "L");    // delimitadores sin caso particular, estado 0, accion L
		matriz[0][23] = new EstadoAccion(null, "144");    // \, ERROR 144


		//Estado 1
		matriz[1][0] = new EstadoAccion(null, "53");      //del, ERROR 53
		matriz[1][1] = new EstadoAccion(null, "54");      //eof, ERROR 54 
		matriz[1][2] = new EstadoAccion(22, "G10");       // &, estado 22, G10
		matriz[1][3] = new EstadoAccion(null, "55");      // * , ERROR 55
		matriz[1][4] = new EstadoAccion(null,"56");       // =, ERROR 56
		matriz[1][5] = new EstadoAccion(null, "57");      // ", ERROR 57
		matriz[1][6] = new EstadoAccion(null, "58");      // digito , ERROR 58
		matriz[1][7] = new EstadoAccion(null, "59");      // .,ERROR 59
		matriz[1][8] = new EstadoAccion(null, "60");      // _ , ERROR 60
		matriz[1][9] = new EstadoAccion(null, "61");      // letra , ERROR 61
		matriz[1][10] = new EstadoAccion(null, "62");     // /, ERROR 62
		matriz[1][11] = new EstadoAccion(null, "63");     // ,, ERROR 63
		matriz[1][12] = new EstadoAccion(null, "64");     // ;, ERROR 64
		matriz[1][13] = new EstadoAccion(null, "65");     // :, ERROR 65
		matriz[1][14] = new EstadoAccion(null, "66");     // (, ERROR 66
		matriz[1][15] = new EstadoAccion(null, "67");     // ), ERROR 67
		matriz[1][16] = new EstadoAccion(null, "68");     // {, ERROR 68
		matriz[1][17] = new EstadoAccion(null, "69");     // }, ERROR 69
		matriz[1][18] = new EstadoAccion(null, "70");     // +, ERROR 70
		matriz[1][19] = new EstadoAccion(null, "71");     // >, ERROR 71
		matriz[1][20] = new EstadoAccion(null, "72");     // o.c, ERROR 72
		matriz[1][21] = new EstadoAccion(null, "73");     // \n, ERROR 73
		matriz[1][22] = new EstadoAccion(null, "140");     // delimitadores sin caso particular, ERROR 140
		matriz[1][23] = new EstadoAccion(null, "145");     // \, ERROR 145


		//Estado 2
		matriz[2][0] = new EstadoAccion(null, "74");      //del, ERROR 74
		matriz[2][1] = new EstadoAccion(null, "75");      // eof, ERROR 75
		matriz[2][2] = new EstadoAccion(null, "76");      // &, ERROR 76
		matriz[2][3] = new EstadoAccion(null, "77");      // * , ERROR 77
		matriz[2][4] = new EstadoAccion(23,"G11");        // =, estado 23, G11 
		matriz[2][5] = new EstadoAccion(null, "78");      // ", ERROR 78
		matriz[2][6] = new EstadoAccion(null, "79");      // digito , ERROR 79
		matriz[2][7] = new EstadoAccion(null, "80");      // .,ERROR 80
		matriz[2][8] = new EstadoAccion(null, "81");      // _ , ERROR 81
		matriz[2][9] = new EstadoAccion(null, "82");      // letra , ERROR 82
		matriz[2][10] = new EstadoAccion(null, "83");     // /, ERROR 83
		matriz[2][11] = new EstadoAccion(null, "84");     // , , ERROR 84
		matriz[2][12] = new EstadoAccion(null, "85");     // ; , ERROR 85
		matriz[2][13] = new EstadoAccion(null, "86");     // : , ERROR 86
		matriz[2][14] = new EstadoAccion(null, "87");     // ( , ERROR 87
		matriz[2][15] = new EstadoAccion(null, "88");     // ) , ERROR 88
		matriz[2][16] = new EstadoAccion(null, "89");     // { , ERROR 89
		matriz[2][17] = new EstadoAccion(null, "90");     // }, ERROR 90
		matriz[2][18] = new EstadoAccion(null, "91");     // +, ERROR 91
		matriz[2][19] = new EstadoAccion(null, "92");     // >, ERROR 92
		matriz[2][20] = new EstadoAccion(null, "93");     // o.c, ERROR 93
		matriz[2][21] = new EstadoAccion(null, "94");     // \n, ERROR 94
		matriz[2][22] = new EstadoAccion(null, "141");     // delimitadores sin caso particular, ERROR 141
		matriz[2][23] = new EstadoAccion(null, "146");     // \, ERROR 146

		//Estado 3
		matriz[3][0] = new EstadoAccion(3, "C'");         // del, estado 3, accion C'
		matriz[3][1] = new EstadoAccion(null, "95");      // eof, error 95
		matriz[3][2] = new EstadoAccion(3, "C'");         // &, estado 3, accion C'
		matriz[3][3] = new EstadoAccion(3, "C'");         // *, estado 3, accion C'
		matriz[3][4] = new EstadoAccion(3, "C'");         // =, estado 3, accion C'
		matriz[3][5] = new EstadoAccion(24, "G13");       // ", estado 24, accion C'
		matriz[3][6] = new EstadoAccion(3, "C'");         // digito, estado 3, accion C'
		matriz[3][7] = new EstadoAccion(3, "C'");         // ., estado 3, accion C'
		matriz[3][8] = new EstadoAccion(3, "C'");         // _, estado 3, accion C'
		matriz[3][9] = new EstadoAccion(3, "C'");         // letra, estado 3, accion C'
		matriz[3][10] = new EstadoAccion(3, "C'");        // /, estado 3, accion C'
		matriz[3][11] = new EstadoAccion(3, "C'");        // ,, estado 3, accion C'
		matriz[3][12] = new EstadoAccion(3, "C'");        // ;, estado 3, accion C'
		matriz[3][13] = new EstadoAccion(3, "C'");        // :, estado 3, accion C'
		matriz[3][14] = new EstadoAccion(3, "C'");        // (, estado 3, accion C'
		matriz[3][15] = new EstadoAccion(3, "C'");        // ), estado 3, accion C'
		matriz[3][16] = new EstadoAccion(3, "C'");        // {, estado 3, accion C'
		matriz[3][17] = new EstadoAccion(3, "C'");        // }, estado 3, accion C'
		matriz[3][18] = new EstadoAccion(3, "C'");        // +, estado 3, accion C'
		matriz[3][19] = new EstadoAccion(3, "C'");        // >, estado 3, accion C'
		matriz[3][20] = new EstadoAccion(3, "C'");        // o.c, estado 3, accion C'
		matriz[3][21] = new EstadoAccion(null, "142");        // \n, ERROR 142
		matriz[3][22] = new EstadoAccion(null, "143");        // delimitadores sin caso particular, ERROR 143
		matriz[3][23] = new EstadoAccion(null, "144");        // \ ERROR 144


		//Estado 4
		matriz[4][0] = new EstadoAccion(26, "G14");       // del, estado 26, accion G14
		matriz[4][1] = new EstadoAccion(26, "G14");       // eof, estado 26, accion G14
		matriz[4][2] = new EstadoAccion(26, "G14");       // &, estado 26, accion G14
		matriz[4][3] = new EstadoAccion(26, "G14");       // *, estado 26, accion G14
		matriz[4][4] = new EstadoAccion(26, "G14");       // =, estado 26, accion G14
		matriz[4][5] = new EstadoAccion(26, "G14");       // ", estado 26, accion G14
		matriz[4][6] = new EstadoAccion(4, "N'");         // digito, estado 4, accion N'
		matriz[4][7] = new EstadoAccion(5, "L");          // ., Estado 26, accion L
		matriz[4][8] = new EstadoAccion(26, "G14");  // _, estado 26, accion G14
		matriz[4][9] = new EstadoAccion(26, "G14");  // letra, estado 26, accion G14
		matriz[4][10] = new EstadoAccion(26, "G14"); // /, estado 26, accion G14
		matriz[4][11] = new EstadoAccion(26, "G14"); // ,, estado 26, accion G14
		matriz[4][12] = new EstadoAccion(26, "G14"); // ;, estado 26, accion G14
		matriz[4][13] = new EstadoAccion(26, "G14"); // :, estado 26, accion G14
		matriz[4][14] = new EstadoAccion(26, "G14"); // ( , estado 26, accion G14
		matriz[4][15] = new EstadoAccion(26, "G14"); // ) , estado 26, accion G14
		matriz[4][16] = new EstadoAccion(26, "G14");     // { , estado 26, accion G14
		matriz[4][17] = new EstadoAccion(26, "G14"); // } , estado 26, accion G14
		matriz[4][18] = new EstadoAccion(26, "G14"); // + , estado 26, accion G14
		matriz[4][19] = new EstadoAccion(26, "G14"); // > , estado 26, accion G14
		matriz[4][20] = new EstadoAccion(26, "G14"); // o.c , estado 26, accion G14
		matriz[4][21] = new EstadoAccion(26, "G14"); // \n , estado 26, accion G14
		matriz[4][22] = new EstadoAccion(26, "G14"); // delimitadores sin caso particular , estado 14, accion G14
		matriz[4][23] = new EstadoAccion(26, "G14"); // \ , estado 14, accion G14



		//Estado 5
		matriz[5][0] = new EstadoAccion(null, "96");     // del, ERROR 96
		matriz[5][1] = new EstadoAccion(null, "97");     // eof, ERROR 97
		matriz[5][2] = new EstadoAccion(null, "98");     // &, ERROR 98
		matriz[5][3] = new EstadoAccion(null, "99");     // *, ERROR 99
		matriz[5][4] = new EstadoAccion(null, "100");     // =, ERROR 100
		matriz[5][5] = new EstadoAccion(null, "101");     // ", ERROR 101
		matriz[5][6] = new EstadoAccion(6, "R");      // digito, estado 6, accion R
		matriz[5][7] = new EstadoAccion(null, "102");     // ., ERROR 102
		matriz[5][8] = new EstadoAccion(null, "103");     // _, ERROR 103
		matriz[5][9] = new EstadoAccion(null, "104");     // letra, ERROR 104
		matriz[5][10] = new EstadoAccion(null, "105");     // /, ERROR 105
		matriz[5][11] = new EstadoAccion(null, "106");     // ,, ERROR 106
		matriz[5][12] = new EstadoAccion(null, "107");     // ;, ERROR 107
		matriz[5][13] = new EstadoAccion(null, "108");     // :, ERROR 108
		matriz[5][14] = new EstadoAccion(null, "109");     // (, ERROR 109
		matriz[5][15] = new EstadoAccion(null, "110");     // ), ERROR 110
		matriz[5][16] = new EstadoAccion(null, "111");     // {, ERROR 111
		matriz[5][17] = new EstadoAccion(null, "112");     // }, ERROR 112
		matriz[5][18] = new EstadoAccion(null, "113");     // +, ERROR 113
		matriz[5][19] = new EstadoAccion(null, "114");     // >, ERROR 114
		matriz[5][20] = new EstadoAccion(null, "115");     // o.c, ERROR 115
		matriz[5][21] = new EstadoAccion(null, "116");     // \n, ERROR 116
		matriz[5][22] = new EstadoAccion(null, "148");     // delimitadores sin caso particular, ERROR 148
		matriz[5][23] = new EstadoAccion(null, "149");     // \, ERROR 149


		//Estado 6
		matriz[6][0] = new EstadoAccion(25, "G15");  // del, estado 25, accion G15
		matriz[6][1] = new EstadoAccion(25, "G15");  // eof, estado 25, accion G15
		matriz[6][2] = new EstadoAccion(25, "G15");  // &, estado 25, accion G15
		matriz[6][3] = new EstadoAccion(25, "G15");  // *, estado 25, accion G15
		matriz[6][4] = new EstadoAccion(25, "G15");   // =, estado 25, accion G15
		matriz[6][5] = new EstadoAccion(25, "G15");  // ", estado 25, accion G15
		matriz[6][6] = new EstadoAccion(6, "R");  // digito , estado 6, accion R
		matriz[6][7] = new EstadoAccion(25, "G15");  // . , estado 25, accion G15
		matriz[6][8] = new EstadoAccion(25, "G15");  // _ , estado 25, accion G15
		matriz[6][9] = new EstadoAccion(25, "G15");  // letra, estado 25, accion G15
		matriz[6][10] = new EstadoAccion(25, "G15"); // / , estado 25, accion G15
		matriz[6][11] = new EstadoAccion(25, "G15"); // , , estado 25, accion G15
		matriz[6][12] = new EstadoAccion(25, "G15"); // ; , estado 25, accion G15
		matriz[6][13] = new EstadoAccion(25, "G15"); // : , estado 25, accion G15
		matriz[6][14] = new EstadoAccion(25, "G15"); // ( , estado 25, accion G15
		matriz[6][15] = new EstadoAccion(25, "G15"); // ), estado 25, accion G15
		matriz[6][16] = new EstadoAccion(25, "G15"); // { , estado 25, accion G15
		matriz[6][17] = new EstadoAccion(25, "G15"); // }, estado 25, accion G15
		matriz[6][18] = new EstadoAccion(25, "G15"); // +, estado 25, accion G15
		matriz[6][19] = new EstadoAccion(25, "G15"); // >, estado 25, accion G15
		matriz[6][20] = new EstadoAccion(25, "G15"); // o.c, estado 25, accion G15
		matriz[6][21] = new EstadoAccion(25, "G15"); // \n, estado 25, accion G15
		matriz[6][22] = new EstadoAccion(25, "G15"); // delimitadores sin caso particular, estado 25, accion G15
		matriz[6][23] = new EstadoAccion(25, "G15"); // \, estado 25, accion G15

		//Estado 7
		matriz[7][0] = new EstadoAccion(27, "G16");     // del, estado 27, accion G16
		matriz[7][1] = new EstadoAccion(27, "G16");  // eof, estado 27, accion G16
		matriz[7][2] = new EstadoAccion(27, "G16");  // &, estado 27, accion G16
		matriz[7][3] = new EstadoAccion(27, "G16");  // *, estado 27, accion G16
		matriz[7][4] = new EstadoAccion(27, "G16");   // =, estado 27, accion G16
		matriz[7][5] = new EstadoAccion(27, "G16");      // ", estado 27, accion G16
		matriz[7][6] = new EstadoAccion(8, "C1'");      // digito, estado 8, accion C1'
		matriz[7][7] = new EstadoAccion(27, "G16");  // ., estado 27, G16
		matriz[7][8] = new EstadoAccion(8, "C1'");  // _, estado 8, accion C1'
		matriz[7][9] = new EstadoAccion(8, "C1'");  // letra, estado 8, accion C1'
		matriz[7][10] = new EstadoAccion(27, "G16"); // /, estado 27, accion G16
		matriz[7][11] = new EstadoAccion(27, "G16"); // ,, estado 27, accion G16
		matriz[7][12] = new EstadoAccion(27, "G16"); // ;, estado 27, accion G16
		matriz[7][13] = new EstadoAccion(27, "G16"); // :, estado 27, accion G16
		matriz[7][14] = new EstadoAccion(27, "G16"); // ( , estado 27, accion G16
		matriz[7][15] = new EstadoAccion(27, "G16"); // ) , estado 27, accion G16
		matriz[7][16] = new EstadoAccion(27, "G16");     // { , estado 27, accion G16
		matriz[7][17] = new EstadoAccion(27, "G16"); // } , estado 27, accion G16
		matriz[7][18] = new EstadoAccion(27, "G16"); // + , estado 27, accion G16
		matriz[7][19] = new EstadoAccion(27, "G16"); // > , estado 27, accion G16
		matriz[7][20] = new EstadoAccion(27, "G16"); // o.c , estado 27, accion G16
		matriz[7][21] = new EstadoAccion(27, "G16"); // \n , estado 27, accion G16	
		matriz[7][22] = new EstadoAccion(27, "G16"); // delimitadores sin caso particular , estado 27, accion G16	
		matriz[7][23] = new EstadoAccion(27, "G16"); // \ , estado 27, accion G16	

		//Estado 8
		matriz[8][0] = new EstadoAccion(28, "G16");  // del, estado 28, accion G16
		matriz[8][1] = new EstadoAccion(28, "G16");  // eof, estado 28, accion G16
		matriz[8][2] = new EstadoAccion(28, "G16");  // &, estado 28, accion G16
		matriz[8][3] = new EstadoAccion(28, "G16");  // *, estado 28, accion G16
		matriz[8][4] = new EstadoAccion(28, "G16");   // =, estado 28, accion G16
		matriz[8][5] = new EstadoAccion(28, "G16");  // ", estado 28, accion G16
		matriz[8][6] = new EstadoAccion(8, "C1'");  // d , estado 8, accion C1'
		matriz[8][7] = new EstadoAccion(28, "G16");  // . , estado 28, accion G16
		matriz[8][8] = new EstadoAccion(8, "C1'");  //  , estado 8, accion C1'
		matriz[8][9] = new EstadoAccion(8, "C1'");  //  , estado 8, accion C1'
		matriz[8][10] = new EstadoAccion(28, "G16"); // / , estado 28, accion G16
		matriz[8][11] = new EstadoAccion(28, "G16"); // , , estado 28, accion G16
		matriz[8][12] = new EstadoAccion(28, "G16"); // ; , estado 28, accion G16
		matriz[8][13] = new EstadoAccion(28, "G16"); // : , estado 28, accion G16
		matriz[8][14] = new EstadoAccion(28, "G16"); // ( , estado 28, accion G16
		matriz[8][15] = new EstadoAccion(28, "G16"); // ), estado 28, accion G16
		matriz[8][16] = new EstadoAccion(28, "G16"); // { , estado 28, accion G16
		matriz[8][17] = new EstadoAccion(28, "G16"); // }, estado 28, accion G16
		matriz[8][18] = new EstadoAccion(28, "G16"); // +, estado 28, accion G16
		matriz[8][19] = new EstadoAccion(28, "G16"); // >, estado 28, accion G16
		matriz[8][20] = new EstadoAccion(28, "G16"); // o.c, estado 28, accion G16
		matriz[8][21] = new EstadoAccion(28, "G16"); // \n, estado 28, accion G16
		matriz[8][22] = new EstadoAccion(28, "G16"); // delimitadores sin caso particular, estado 28, accion G16
		matriz[8][23] = new EstadoAccion(28, "G16"); // \, estado 28, accion G16

		//Estado 9
		matriz[9][0] = new EstadoAccion(null, "117");     // del, ERROR 117
		matriz[9][1] = new EstadoAccion(null, "118");     // eof, ERROR 118
		matriz[9][2] = new EstadoAccion(null, "119");     // &, ERROR 119
		matriz[9][3] = new EstadoAccion(null, "120");     // *, ERROR 120
		matriz[9][4] = new EstadoAccion(null, "121");     // =, ERROR 121
		matriz[9][5] = new EstadoAccion(null, "122");     // ", ERROR 122
		matriz[9][6] = new EstadoAccion(null, "123");      // digito, ERROR 123
		matriz[9][7] = new EstadoAccion(null, "124");     // ., ERROR 124
		matriz[9][8] = new EstadoAccion(null, "125");     // _, ERROR 125
		matriz[9][9] = new EstadoAccion(null, "126");     // letra, ERROR 126
		matriz[9][10] = new EstadoAccion(10, "L");     // /, estado 10, accion L
		matriz[9][11] = new EstadoAccion(null, "127");     // ,, ERROR 127
		matriz[9][12] = new EstadoAccion(null, "128");     // ;, ERROR 128
		matriz[9][13] = new EstadoAccion(null, "129");     // :, ERROR 129
		matriz[9][14] = new EstadoAccion(null, "130");     // (, ERROR 130
		matriz[9][15] = new EstadoAccion(null, "131");     // ), ERROR 131
		matriz[9][16] = new EstadoAccion(null, "132");     // {, ERROR 132
		matriz[9][17] = new EstadoAccion(null, "133");     // }, ERROR 133
		matriz[9][18] = new EstadoAccion(null, "134");     // +, ERROR 134
		matriz[9][19] = new EstadoAccion(null, "135");     // >, ERROR 135
		matriz[9][20] = new EstadoAccion(null, "136");     // o.c, ERROR 136
		matriz[9][21] = new EstadoAccion(null, "137");     // \n, ERROR 137
		matriz[9][22] = new EstadoAccion(null, "150");     // delimitadores sin caso particular, ERROR 150
		matriz[9][23] = new EstadoAccion(null, "151");     // \, ERROR 151

		//Estado 10
		matriz[10][0] = new EstadoAccion(10, "L");      // del, estado 10, accion L 
		matriz[10][1] = new EstadoAccion(null, "138");      // eof, ERROR 132
		matriz[10][2] = new EstadoAccion(10, "L");      // &, estado 10, accion L 
		matriz[10][3] = new EstadoAccion(10, "L");     // * , estado 10, accion L
		matriz[10][4] = new EstadoAccion(10, "L");     // =, estado 10, accion L
		matriz[10][5] = new EstadoAccion(10, "L");      // ", estado 10, accion L
		matriz[10][6] = new EstadoAccion(10, "L");      // digito , estado 10, accion L
		matriz[10][7] = new EstadoAccion(10, "L");  // ., estado 10, accion L
		matriz[10][8] = new EstadoAccion(10, "L");  // _ , estado 10, accion L
		matriz[10][9] = new EstadoAccion(10, "L");      // letra , estado 10, accion L
		matriz[10][10] = new EstadoAccion(10, "L");    // /, estado 10, accion L 
		matriz[10][11] = new EstadoAccion(10, "L");    // ,, estado 10, accion L
		matriz[10][12] = new EstadoAccion(10, "L");    // ;, estado 10, accion L
		matriz[10][13] = new EstadoAccion(10, "L");    // :, estado 10, accion L 
		matriz[10][14] = new EstadoAccion(10, "L");    // (, estado 10, accion L
		matriz[10][15] = new EstadoAccion(10, "L");    // ), estado 10, accion L
		matriz[10][16] = new EstadoAccion(10, "L"); // {, estado 10, accion L
		matriz[10][17] = new EstadoAccion(10, "L"); // }, estado 10, accion L
		matriz[10][18] = new EstadoAccion(10, "L"); // +, estado 10, accion L
		matriz[10][19] = new EstadoAccion(10, "L"); // >, estado 10, accion L
		matriz[10][20] = new EstadoAccion(10, "L");     // o.c, estado 10, accion L 
		matriz[10][21] = new EstadoAccion(0, "L");     // \n, estado 0, accion L 
		matriz[10][22] = new EstadoAccion(10, "L");     // delimitadores sin caso particular, estado 10, accion L 
		matriz[10][23] = new EstadoAccion(10, "L");     // \, estado 10, accion L 

	}

	public static String accion (Integer estado, char car) {
		int j=-1; // indice que correspondera a la columna de la matriz segun el caracter
		switch (car) { 
		case ' ':
			j=0;     // Espacio = columna 0
			break;
		case '&':
			j=2;    // Ampersand = columna 2
			break;
			case '*': 
			j=3;    // Asterisco = columna 3
			break;
		case '=':
			j=4;	// Igual = columna 4
			break;
		case '"':	
			j=5;	// Comillas = columna 5
			break;
		case '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9':
			j=6;	// Digitos = columna 6
		break;
		case '.':
			j=7;	// Punto = columna 7
			break;
		case '_':
			j=8;	// Guion bajo = columna 8
			break;   			
		case 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' , 'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' , 'q' , 'r' , 's' , 't' , 'u' , 'v' , 'w' , 'x' , 'y' , 'z' ,
		'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' , 'K' , 'L' ,'M' , 'N' , 'O' , 'P' , 'Q' , 'R' , 'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z':
			j=9;	// Todas las letras (mayusculas y minusculas) = columna 9
		break;
		case '/':
			j=10;	// Barra = columna 10
			break;
		case ',':
			j=11;	// Coma = columna 11
			break;
		case ';':
			j=12;	// Punto y coma = columna 12
			break;
		case ':':
			j=13;	// Dos puntos = columna 13
			break;
		case '(':
			j=14;	// Parentesis izquierdo = columna 14
			break;
		case ')':
			j=15;	// Parentesis derecho = columna 15
			break;
		case '{':
			j=16;	// Llave izquierda = columna 16
			break;
		case '}':
			j=17;	// Llave derecha = columna 17
			break;
		case '+':	
			j=18;	// Suma = columna 18
			break;
		case '>':
			j=19;	 // Mayor que = columna 19
			break;
		case '\n':
			j=21;	// Salto de linea = columna 21
			break;
		case '\t', '\b', '\f', '\r': // Delimitadores = columna 22
			j=22;
		break;
		case '\\':
			j=23;	// Barra invertida = columna 23
			break;  		
		default:
			j=20;	// Cualquier otro caracter no contemplado = columna 20
			break;
		}
		return matriz[estado][j].getAccion();
	}

	public static Integer estado (Integer estado, char car) {
		int j=-1;
		switch (car) {
		case ' ':
			j=0;	// Espacio
			break;
		case '&':
			j=2;	// Ampersand
			break;
		case '*':
			j=3;	// Asterisco
			break;
		case '=':
			j=4;	// Igual
			break;
		case '"':
			j=5;	// Comillas
			break;
		case '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9':
			j=6;	// Digitos
		break;
		case '.':	
			j=7;	// Punto
			break;
		case '_' :
			j=8;	// Guion bajo
			break;
		case 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' , 'j' , 'k' , 'l' , 'm' , 'n', 'o' , 'p' , 'q' , 'r' , 's' , 't' , 'u' , 'v' , 'w' , 'x' , 'y' , 'z' ,
		'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' , 'K' , 'L' ,'M' , 'N' , 'O' , 'P' , 'Q' , 'R' , 'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z':
			j=9;	// Letras
		break;
		case '/' :	
			j=10;	// Barra
			break;
		case ',':
			j=11;	// Coma
			break;
		case ';':
			j=12;	// Punto y coma
			break;
		case ':':
			j=13;	// Dos puntos
			break;
		case '(':
			j=14;	// Parentesis izquierdo
			break;
		case ')':
			j=15;	// Parentesis derecho
			break;
		case '{':
			j=16;	// Llave izquierda
			break;
		case '}':	
			j=17;	// Llave derecha
			break;
		case '+':
			j=18;	// Suma
			break;
		case '>':
			j=19;	// Mayor que
			break;
		case '\n':	
			j=21;	// Salto de linea
			break;
		case '\t', '\b', '\f', '\r': // caso delimitadores
			j=22;	// delimitadores
		break;
		case '\\':
			j=23;	// Barra invertida
			break;
		default:
			j=20;	// Otros caracteres
			break;
		}	

		// Devuelve el siguiente estado segun el estado actual y caracter dado
		return matriz[estado][j].getEstado();
	}

}
