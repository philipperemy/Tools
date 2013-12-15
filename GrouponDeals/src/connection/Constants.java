package connection;

public class Constants
{
    public enum City
    {
        AIX_EN_PROVENCE("aix-en-provence"),
        AVIGNON("avignon"),
        BORDEAUX("bordeaux"),
        COLMAR("colmar"),
        PARIS_HAUTS_DE_SEINE("paris-hauts-de-seine"),
        LE_MANS("le-mans"),
        MONTAUBAN("montauban"),
        NICE("nice"),
        PARIS_EXTRA("paris-extra"),
        PAU("pau"),
        ROUEN("rouen"),
        STRASBOURG("strasbourg"),
        PARIS_VAL_DE_MARNE("paris-val-de-marne"),
        AMIENS("amiens"),
        BAYONNE_ANGLET_BIARRITZ("bayonne-anglet-biarritz"),
        BREST("brest"),
        DIJON("dijon"),
        LA_BAULE("la-baule"),
        LILLE("lille"),
        LYON("lyon"),
        MONTPELLIER("montpellier"),
        NIMES("nimes"),
        PARIS_SUD_EST("paris-sud-est"),
        PERPIGNAN("perpignan"),
        SAINT_ETIENNE("saint-etienne"),
        TROYES("troyes"),
        PARIS_VAL_D_OISE("paris-val-d-oise"),
        ANGERS("angers"),
        BELFORT("belfort"),
        CAEN("caen"),
        PARIS_ESSONNE("paris-essonne"),
        LA_REUNION("la-reunion"),
        LILLE_METROPOLE("lille-metropole"),
        LYON_EST("lyon-est"),
        MULHOUSE("mulhouse"),
        OISE_EXTRA("oise-extra"),
        PARIS_CENTRE("paris-centre"),
        POITIERS("poitiers"),
        SAINT_MALO("saint-malo"),
        TOULON("toulon"),
        VALENCE("valence"),
        ANNECY("annecy"),
        BESANCON("besancon"),
        CHAMBERY("chambery"),
        FREJUS("frejus"),
        LA_ROCHELLE("la-rochelle"),
        LIMOGES("limoges"),
        MARSEILLE("marseille"),
        NANCY("nancy"),
        ORLEANS("orleans"),
        PARIS_OUEST("paris-ouest"),
        REIMS("reims"),
        PARIS_SEINE_ET_MARNE("paris-seine-et-marne"),
        TOULOUSE("toulouse"),
        VALENCIENNES("valenciennes"),
        ANTIBES_CANNES("antibes-cannes"),
        BEZIERS("beziers"),
        CLERMONT_FERRAND("clermont-ferrand"),
        GRENOBLE("grenoble"),
        LE_HAVRE("le_havre"),
        LORIENT("lorient"),
        METZ("metz"),
        NANTES("nantes"),
        PARIS("paris"),
        PARIS_NORD_EST("paris-nord-est"),
        RENNES("rennes"),
        PARIS_SEINE_SAINT_DENIS("paris-seine-saint-denis"),
        TOURS("tours"),
        PARIS_VERSAILLES_YVELINES("paris-versailles-yvelines");

        private String city;

        private City(String city)
        {
            this.city = city;
        }

        public String getName()
        {
            return city;
        }

        public City[] getAllCities()
        {
            return values();
        }

        public static City fromString(String text)
        {
            if (text != null)
            {
                for (City b : City.values())
                {
                    if (text.equalsIgnoreCase(b.getName()))
                    {
                        return b;
                    }
                }
            }
            return null;
        }
    }
}
