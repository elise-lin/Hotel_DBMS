------DBMS HOTEL PROJECT

CREATE INDEX Repair_RID_index
ON Repair
USING BTREE (RID);

CREATE INDEX Booking_price_index
ON Booking 
USING BTREE (price);

CREATE INDEX Repair_repairDate_index
ON Repair
USING BTREE (repairDate);

CREATE INDEX Booking_hotelID_index
ON Booking
USING BTREE (hotelID);

CREATE INDEX MaintenanceCompany_cmpID_index
ON MaintenanceCompany
USING BTREE (cmpID);

CREATE INDEX Repair_mCompany_index
ON Repair
USING BTREE (mCompany);

CREATE INDEX Booking_hotelID_index
ON Booking
USING BTREE (hotelID);

CREATE INDEX Room_roomNo_index
ON Room 
USING BTREE (roomNo);

CREATE INDEX Customer_fName_index
ON Customer
USING BTREE (fName);

CREATE INDEX Customer_lName_index
ON Customer 
USING BTREE (lName);

CREATE INDEX Booking_bookingDate_index
ON Booking 
USING BTREE (bookingDate);

CREATE INDEX Room_roomNo_index
ON Room
USING BTREE (hotelID);

CREATE INDEX Customer_customerID_index
ON Customer
USING BTREE (customerID);

CREATE INDEX Repair_roomNo_index
ON Repair
USING BTREE (roomNo);

--Booking
--bookingDate 3
--hotelID 5
--price 9

--Room 
--RoomNo 4
--hoteLID 2

--Customer
--fName 4
--lName 4
--customerID 2

--Maintenance Company
--cmpID 5

--Repair
--RID 10
--roomNo 2
--mCompany 5
--repairDate 6
--hotelID 1
