-- getViewStaticRoomByIncome
SELECT room.id, room.name, room.type, 
(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST(b.checkin, :startDate) )) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) as days, 
(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST(b.checkin, :startDate))*room.price) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) as income 
FROM Room room  WHERE
(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST (b.checkin, :startDate) )) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) IS NOT NULL ORDER BY income DESC , days DESC


-- get Booking Of Room
SELECT br.id , c.fullname, (CASE WHEN br.checkin > :startDate THEN br.checkin ELSE :startDate END) as Day_Start, (CASE WHEN br.checkout< :endDate THEN  br.checkout ELSE :endDate END) as Day_End , 
r.price , DATEDIFF( LEAST(:endDate , br.checkout ) , GREATEST(:startDate , br.checkin) ) as TotalDays , 
(r.price  *  DATEDIFF( LEAST(:endDate , br.checkout ) , GREATEST(:startDate , br.checkin) ) ) as income 
FROM Booking B , Client c, BookedRoom br, Room r 
WHERE br.room.id = :idRoom and c.id = B.client.id and B.id  = br.booking.id and br.room.id = r.id and br.checkin < :endDate and br.checkout > :startDate  order by income DESC

