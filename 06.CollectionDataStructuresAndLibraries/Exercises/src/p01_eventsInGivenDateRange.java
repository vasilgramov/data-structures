import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class p01_eventsInGivenDateRange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:ss");

        TreeMap<Date, ArrayList<String>> events = new TreeMap<>();

        int eventsCount = Integer.parseInt(in.nextLine());
        for (int i = 0; i < eventsCount; i++) {
            String[] currentEventData = in.nextLine().split("\\|");

            String eventName = currentEventData[0].trim();
            String eventDateAsString = currentEventData[1].trim();

            try {
                Date date = formatter.parse(eventDateAsString);

                if (!events.containsKey(date)) {
                    events.put(date, new ArrayList<>());
                }

                ArrayList<String> currentEventsNames = events.get(date);
                currentEventsNames.add(eventName);
                events.put(date, currentEventsNames);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        int ranges = Integer.parseInt(in.nextLine());
        for (int i = 0; i < ranges; i++) {
            String[] currentRangeData = in.nextLine().split("\\|");
            String fromAsString = currentRangeData[0].trim();
            String toAsString = currentRangeData[1].trim();

            NavigableMap<Date, ArrayList<String>> eventsInRange = new TreeMap<>();

            for ( Map.Entry<Date, ArrayList<String>> entry : events.entrySet() ) {
                Date key = entry.getKey();
                ArrayList<String> value = entry.getValue();

                try {
                    Date from = formatter.parse(fromAsString);
                    Date to = formatter.parse(toAsString);

                    if(!from.after(key) && !to.before(key)) {
                        eventsInRange.put(key, value);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


            System.out.println(eventsInRange.size());
            for (Map.Entry<Date, ArrayList<String>> entry : eventsInRange.entrySet()) {
                Date key = entry.getKey();
                ArrayList<String> value = entry.getValue();

                for (String eventName : value) {
                    System.out.println(eventName + " -> " + key);
                }
            }

        }
    }
}

