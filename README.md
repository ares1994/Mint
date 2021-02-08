# Mint

I chose to work with the MVVM Application architecture while also using a Repository to affirm the seperation of concerns.
DaggerHilt was used for Dependency Injection in this project.
The project a few core blocks namely:

The ViewModel, The repository, The AppModule and The BinList interface,


The repository in this case contains the suspend function that makes the network call using the card number passed to it and the injected retrofit object
The viewModel procures the data from the repo and stores it in such a way that can be easily observed by the UI component used which in this case is the Actvity.
In this case however, that data isn't being observed because of the timing specified in the app requirements document which specifies when the data retireved from the server is to be 
displayed.

The AppModule contains the components required to make the BinList API's retrofit object which is then injected into the component(s) that need(s) it.

And the BinList Interface contains the retrofit method for making the card validation request for making the card validation request.


The OCR part of the app wasn't implemented because of the time constraint.
The plan is to use Firebase's Machine Learning Kit, however putting that together would take me a day or 2 more which exceeds my given timeframe. 
If given a time extention, I can put that together as well as a simple UI test.
