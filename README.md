# recolib

Recolib is a recommendation library which uses SVD algorithm.
 
#### Dependencies
- [Maven][maven]
- [Mahout][mahout]
- [Apache Commons][apachecommons]

### Documentation
Every function uses SVD algorithm for compression of the data.
The format of your data needs to be 
| User Id | Product Id | Product Rating |
|---------|------------|----------------|
*table is self-explanatory*

Load the file in datamodel class
```sh
	String file_path = "data/ratings.dat";
	String split = "::";
	DataModel m = new FileDataModel(new File(file_path), split);
```
#### Pearson-Correlation
For using pearson correlation similarity you need to use PearsonCorrelationSimilarityWithSVD function

###### parameters
- Data Model which you loaded
```sh
UserSimilarity us = Recolib.PearsonCorrelationSimilarityWithSVD(m);
```
#### Log-likelihood 
For using log-likelihood similarity you need to use LogLikelihoodSimilarityWithSVD function

###### parameters
- Data Model which you loaded
```sh
UserSimilarity us = Recolib.LogLikelihoodSimilarityWithSVD(m);
```
#### Euclidean Distance
For using Euclidean distance similarity you need to use EuclideanDistanceSimilarityWithSVD function

###### parameters
- Data Model which you loaded
```sh
UserSimilarity us = Recolib.EuclideanDistanceSimilarityWithSVD(m);
```
#### Tanimoto Coefficient Similarity
For using Tanimoto Coefficient similarity you need to use TanimotoCoefficientSimilarityWithSVD function

###### parameters
- Data Model which you loaded
```sh
UserSimilarity us = Recolib.TanimotoCoefficientSimilarityWithSVD(m);
```

### Version
3.2.4


### Installation
Clone the library in apache mahout and you are ready to go!
command line
```sh
git clone https://github.com/thecodersblock/recolib.git
```
License
----

MIT


**Free Software, Hell Yeah!**

**जनहित में जारी**


  [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [@thomasfuchs]: <http://twitter.com/thomasfuchs>
   [maven]: <https://github.com/apache/maven>
   [mahout]: <https://github.com/apache/mahout>
   [apachecommons]: <https://github.com/apache/commons-math>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [keymaster.js]: <https://github.com/madrobby/keymaster>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]:  <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>


