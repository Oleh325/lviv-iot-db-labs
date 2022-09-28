SELECT trip_no, name, plane, town_from, town_to,
CASE WHEN TIMEDIFF(time_in, time_out) > 0 
    THEN TIMEDIFF(time_in, time_out)
    ELSE TIMEDIFF(date_add(time_in, INTERVAL 1 DAY), time_out)
END AS flight_time
FROM trip JOIN company ON trip.ID_comp = company.ID_comp;