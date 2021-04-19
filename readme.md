#Delivery Manager
![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/La-Casa-Dorada.jpg?token=AQ5226H5F66Y4DFMMN7YRT3APTYKW)
###1. Introduction
This application was developed to help with the administration of customers, employees, products and orders of the restaurant La Casa Dorada.

### 2. Entities
In order to fulfill the features required by the client, the following entities have been defined:
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
![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/menu-imgs/menu-bar.PNG?token=AQ5226EGEL42QZKXPJBV4L3APTYR4)

**Context menus**
These menus are displayed after selecting a cell in the table and pressing the right button of the mouse.
![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/menu-imgs/context-menu.png?token=AQ5226BG44XNTE6UEPJRB2LAPTYTQ)

![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/menu-imgs/context-menu2.png?token=AQ5226GCWDN4FC7QC2YPNXTAPTYVA)

**Add entity example:**
![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/orders-imgs/add-order.PNG?token=AQ5226BOCQ5DMKVJE2SGWJTAPTZMY)

**Modify entity example:**
![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/products-imgs/modify-product.PNG?token=AQ5226ED2JWVL5WABULUNODAPTZQM)

**Visualize entity example:**
![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/ingredientes-imgs/ingredients-table.PNG?token=AQ5226B5QTMTAUI5E5HRTDDAPTZTO)


### 3. Files
The application is in the capacity of import and export information corresponding to the restaurant. These features are specified below.

#### 3.1 Import
The application can import three types of data: customers, products and orders. In order to import data from a file, the file must have the extension .csv and a different amount of separators that indicates the position of the attributes depending on the type.
▶ **Order:** Three separators are required.
**Example:**
Arroz con pollo/familiar;Arroz con pollo/grande,2;2,nada,ENTREGADO,111,123
▶ **Product:** Two separators are required.
**Example:** Arroz con pollo;arroz;pollo,10000;20000,familiar;grande,comida
▶ **Customer:** One separator is required.
**Example:** Diego,Hidalgo,12345,calle 25 #5-33,nada

![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/files-imgs/import-img.PNG?token=AQ5226GYPI5JVMTE34DNZ5LAPT2MQ)

#### 3.2 Export
The application can export three types of data: employees, products and orders. The extension of the file will be .csv. The user must choose a range of dates and hours, a save path and a separator.

![](https://raw.githubusercontent.com/Diego-Hidalgo/delivery-manager/create-GUI/images/files-imgs/export-img.PNG?token=AQ5226FBUFGHQFTIJPFMAMDAPT2KO)

### 4. Changes
The new changes can be found in the next link: file with changes.

### 5. Contributors
▶ Diego Hidalgo. [GitHub profile.](https://github.com/Diego-Hidalgo "GitHub profile.")
▶ Brian Romero. [GitHub profile](https://github.com/BrianR18 "GitHub profile")

### 6. Additional information
This application was developed using Java 8, JavaFX and CSS
