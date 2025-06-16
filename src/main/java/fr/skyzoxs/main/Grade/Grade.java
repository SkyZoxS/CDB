    package fr.skyzoxs.main.Grade;

    public class Grade {
        public static int[] bounds = {
                1000,           // > Grain De Sable                 (i == 1)
                10000,          // > Touriste du Désert             (i == 2)
                25000,          // > Sableux Timide                 (i == 3)
                50000,          // > Chameau en Formation           (i == 4)
                75000,          // > Marchand d'Oasis               (i == 5)
                100000,         // > Dune-Divin                     (i == 6)
                125000,         // > Sablé au Beurre                (i == 7)
                150000,         // > Pharaon en Vacances            (i == 8)
                200000,         // > Gardien du Mojito              (i == 9)
                250000,         // > Démon en Sandales              (i == 10)
                300000,         // > Légende en short               (i == 11)

        };

        public static String[] gradeName = {
                "Grain de Sable",
                "Touriste du Désert",
                "Sableux Timide",
                "Chameau en Formation",
                "Marchand d'Oasis",
                "Dune-Divin",
                "Sablé au Beurre",
                "Pharaon en Vacances",
                "Gardien du Mojito",
                "Démon en Sandales",
                "Légende en short",
        };

        public static int getGradeIndex(int reputation) {
            if (reputation < 0) {
                throw new IllegalArgumentException("The reputation cannot be negative.");
            }
            if (reputation >= Grade.bounds[Grade.bounds.length-1]) {
                return Grade.gradeName.length-1;
            }

            int i = 0;
            while (i < Grade.bounds.length && reputation >= Grade.bounds[i]) {
                i++;
            }
            return i;
        }

        public static String getGradeName(int reputation) {
            return Grade.gradeName[Grade.getGradeIndex(reputation)];
        }
    }
