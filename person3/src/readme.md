1. Each item like book, toy,... is an object of Product
About Order class: Attribute: id <is the time that the Payment process is successfully>, list of order item in the cart, total_purchase_price, total_selling_price

2. In main function:
- Login -> find the user name in each data file to identify that if the user is Employee or Manager -> Authenticate the password.

- After login successfully:
    + load all data of product.csv, order.csv file.
    
    if: 
    + getOrder(productID):
        Initilaize a listOrder (each element of order is a map or array to save id and quantity) to save cart, if the customer want to change item in cart, we can modify in the list. This also have remove from listOrder function.        
        
        Iterate through listOrder:  <Polymorphism>
            getDetail of each item by ID 
            Reduce quantity -> update to quantity attribute of product 
            Calculate total_price on each item 
        Update purchase_price_order, selling_price_order, profit attribute
        Call the processPayment() method
        save to order.csv file 
        
    + processPayment():
        get the order_time to become order_id to easily statistic on revenue and profit
        <Temporarily calculate total revenue, and then we can think about develop the function of sort the revenue on each Product>
        
        show the bill
    + addProduct()
    + removeProduct()
    
