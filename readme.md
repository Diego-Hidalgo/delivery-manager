#Delivery Manager
![](https://github.com/Diego-Hidalgo/delivery-manager/blob/create-GUI/images/La-Casa-Dorada.jpg?raw=true)

###Introduction
This application was developed to help with the administration of customers, employees, products and orders of the restaurant La Casa Dorada.

###Entities
In order to fulfill the features required by the client, the following entities
have been defined:

▶ Customer

▶ Employee

▶ User

▶ Ingredients

▶ Dishtype

▶ Product

▶ Order

For each entity, the application is in the capacity of manage it, this means add, modify, enable/disable and delete them. In order to do this, the user must acces to the different menus or double-click a selected cell (modify a field). These are:

**Bar menu:**
By using this menu the user can access to the add options and visualize the table of a selected entity, from there the user can modify, enalbe/unable and delete them.


**Context menus**
These menus are displayed after selecting a cell in the table and pressing the right button of the mouse.


**Add entity example:**


**Modify entity example:**


**Visualize entity example:**



###Files
The application is in the capacity of import and export information corresponding to the restaurant. These features are specified below.

###Import
The application can import three types of data: customers, products and orders. In order to import data from a file, the file must have the extension .csv and a different amount of separators that indicates the position of the attributes depending on the type.

▶ **Order:** Three separators are required.

**Example:**
Arroz con pollo/familiar;Arroz con pollo/grande,2;2,nada,ENTREGADO,111,123

▶ **Product:** Two separators are required.

**Example:** Arroz con pollo;arroz;pollo,10000;20000,familiar;grande,comida

▶ **Customer:** One separator is required.

**Example:** Diego,Hidalgo,12345,calle 25 #5-33,nada



###Export
The application can export three types of data: employees, products and orders. The extension of the file will be .csv. The user must choose a range of dates and hours, a save path and a separator.

###Changes
The new changes can be found in the next link: file with changes.

###Contributors
▶ Diego Hidalgo. [GitHub profile.](https://github.com/Diego-Hidalgo "GitHub profile.")

▶ Brian Romero. [GitHub profile](https://github.com/BrianR18 "GitHub profile")

###Additional information
This application was developed using Java 8, JavaFX and CSS
