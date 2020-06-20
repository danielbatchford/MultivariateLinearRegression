# Simple Multivariate Linear Regression
## About
This is a multivariate linear regression library used to classify n-variable data. It uses a sigmoid cost function in order to classify data read from a file. It can then later predict the classification of a new data point, as well as calculate accuracy across a file of test data.

This model is designed for linear seperable data only. It also assumes the data fits into two classes.

## How To Use
Begin by creating a `Parser` object.  
`Parser myParser = new Parser()`  
This will transform your data into a list of `DataPair` objects.
A `DataPair` object contains a `Float[]` list of values and a single `Integer` result (either 0 or 1).

Next, read your data:  
`List<DataPair> myData = myParser.load("filename.txt", Format.TEXT, arg3)`, where `arg3` is whether to omit the header (ignore the first line).

Create a Regression model:  
`Regression myModel = new MultivariateLinearRegression(myData, 0.01f)`

Train the model:  
`myModel.learn(arg1)`, where `arg1` is whether to print out progress to the console.

Testing output can be obtained using:  
`myModel.test(testingData.txt)`  
This will print out testing accuracy to the console.

Finally, values can be predicted from the model:  
`myModel.predict(arg1)`, where `arg1` is a `Float[]` of input variables. A single float between 0 and 1 will be returned.
This represents the closeness to each class in the classification, 0 & 1.

## Saving And Loading Coefficients
Coefficients of the classification function can be saved to a text file, for later re-use.

Coefficients can be written to a text file using:  
`myModel.saveCoefficients("filename.txt")`  

Coefficients can be read using:  
`myModel.loadCoefficients("filename.txt")` 

## Valid Data Formats
Currently, the following formats are supported:
- `.txt`
- `.csv`

Text data should be in the following format:
`x0 x1 x2 x3 ... xi ... xn r\n` where `xi` represents the i'th data value, `r` represents the result and `\n` is a new line character.

Similarly, CSV data should be in the following format:
`x0, x1, x2, x3, ... xi, ... xn, r\n`

## Contributing

Feel free to report bugs or contribute to this project. Please follow the style guide included. (`style.editorconfig`)
