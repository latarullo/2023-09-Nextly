/*
 * SCHEMA
 * ---------------------------------------------
 * 
 * ----------
 * SUPPLIER |
 * ----------
 * SUPPLIER_CODE (PK)
 * SUPPLIER_NAME
 * CITY
 * 
 * PART|
 * -----
 * CODE_PART (PK)
 * NAME_PART
 * PRICE
 * 
 * -----
 * CAR |
 * -----
 * CODE_CAR (PK)
 * NAME_CAR
 * TYPE
 * 
 * 
 * --------
 * SUPPLY |
 * --------
 * CODE_SUPPLIER
 * CODE_PIECE
 * CODE_CAR
 *
 * 
 * Write an SQL command that is able to query the suppliers located in the city named
 * “VITORIA” that provide the part code “MOTOR” for the car coded “KOMBI”, with their
 * respective prices.
 * 
 * Example:
 * 
 * SUPPLIER         PRICE
 * ---------------- --------
 * Supplier A        1,000
 * Supplier B        1,500 
 */


/*
 * Field names as specified in the sample result
 */
SELECT A.SUPPLIER_NAME AS SUPPLIER, D.PRICE

/*
 * Joining all tables (might not join them all - check WHERE clause details)
 */
FROM SUPPPLIER A
INNER JOIN SUPPLY B
ON B.CODE_SUPPLIER = A.SUPPLIER_CODE
INNER JOIN CAR C
ON C.CODE_CAR = B.CODE_CAR
INNER JOIN PART D
ON D.CODE_PART = B.CODE_PIECE

/*
 * I am assuming PART_CODE and CODE_CAR (PK fields) are CHAR/VARCHAR in this case joins would not be needed since the comparison could be made on the domain table
 */
WHERE A.CITY = 'VITORIA'
AND D.CODE_PART = 'MOTOR' 
AND C.CODE_CAR = 'KOMBI'
