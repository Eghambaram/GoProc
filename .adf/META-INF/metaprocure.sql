DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS UserDetails;

CREATE TABLE Categories
(
  id VARCHAR(30),
  parentId VARCHAR(30),
  name VARCHAR(3000),
  source VARCHAR(30)
  );

CREATE TABLE Cart
(
  id VARCHAR(30),
  poNo VARCHAR(300),
  vendorName VARCHAR(3000),
  vendorSiteCode VARCHAR(300),
  productCategory VARCHAR(300),
  productTitle VARCHAR(3000),
  unitPrice VARCHAR(300),
  imageUrl VARCHAR(3000),
  checked VARCHAR(300),
  source VARCHAR(30),
  uom VARCHAR(300),
  quantity VARCHAR(300),
  amount VARCHAR(300)
  );
CREATE TABLE UserDetails
(
  Us_ID VARCHAR(30),
  User_Name VARCHAR(30),
  Hashed_Password VARCHAR(30)
  );
COMMIT;
