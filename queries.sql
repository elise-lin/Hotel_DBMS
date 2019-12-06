--TESTING INDEXING WITH QUERIES 8-15

--8
SELECT COUNT(r.roomNo) 
FROM (SELECT roomNo FROM Room WHERE hotelID =  25) AS r 
		WHERE r.roomNo NOT IN (SELECT b1.roomNo 
								FROM Booking b1 
								WHERE b1.hotelID = 25);

--9
SELECT DISTINCT count(b.roomNo) 
FROM Booking b 
WHERE b.hotelID = 25;

--10
SELECT roomNo 
FROM Booking 
WHERE 25 = hotelID AND 
bookingDate BETWEEN TO_DATE('20021202', 'YYYYMMDD') 
AND TO_DATE('20021202', 'YYYYMMDD') + 7;

--11
SELECT roomNo 
FROM Booking 
WHERE bookingDate BETWEEN TO_DATE('20000101' , 'YYYYMMDD') AND TO_DATE('20181228' , 'YYYYMMDD') ORDER BY price DESC LIMIT 3;

--12
SELECT b.price 
FROM Booking b, Customer c 
WHERE c.fName = 'rzqs' AND c.lName = 'eyeg' AND b.customer=c.customerID ORDER BY price DESC LIMIT 3;

--13
SELECT SUM(b.price) total 
FROM Customer c, Booking b 
WHERE c.fName = 'rzqs' AND c.lName = 'eyeg' AND c.customerID = b.customer AND b.hotelID = '536' AND b.bookingDate BETWEEN TO_DATE('20160726', 'YYYYMMDD') AND TO_DATE('20190101', 'YYYYMMDD');

--14
SELECT rep.rID, rep.repairType, rep.hotelID, rep.roomNo 
FROM Repair rep, MaintenanceCompany mc 
WHERE 'iqcq' = mc.name AND rep.mCompany = mc.cmpID;

--15
SELECT mc.name, COUNT(rep.rID) 
FROM MaintenanceCompany mc, Repair rep 
WHERE mc.cmpID=rep.mCompany 
GROUP BY mc.name 
ORDER BY COUNT(rep.rID) DESC LIMIT 5;

--16
SELECT EXTRACT(YEAR FROM rep.repairDate), COUNT(rep.rID) 
FROM Room r, Repair rep 
WHERE r.roomNo = '1' AND r.hotelID = '1' AND r.roomNo = rep.roomNo 
GROUP BY EXTRACT(YEAR FROM rep.repairDate) 
ORDER BY EXTRACT(YEAR FROM rep.repairDate) DESC
