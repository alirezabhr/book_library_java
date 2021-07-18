### A Program For Books In Libraries
This application is designed to be used as a library
program for recording data such as students, books and
records with GUI(Graphical User Interface).

You can set how to read and write data from database
with adaptors. There are 4 main adaptors: _FixRecordFixString_,
_FixRecordDynamicString_, _DynamicRecordFixString_ and 
_DynamicRecordDynamicString_.\
These adaptors are inherited from a Record Adaptor class, and a
String Adaptor interface. Also Record Adaptors are inherited
from a base Adaptor class, which is abstract class.

If you want to extend this project and implement another class,
at first you need to add your class in models, and inherit from Entity class. 
After that you should implement just 3 methods and override a clone method.
Don't forget to add setter and getters for your class, even if you don't want
to implement getters. You need to add getters for your Table Columns in your
View.\
After creating and adding a model you should add a config, and a binder in
controller.\
In Views you have to implement your entity view, and your entity forms.
Your entity view inherits from EntityView class, and your entity form should
inherit from BaseForm class.
