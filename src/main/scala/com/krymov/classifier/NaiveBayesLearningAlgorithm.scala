package com.krymov.classifier

/**
 * Обучающий алгоритм классификации
 */
class NaiveBayesLearningAlgorithm {

  private var examples: List[(String, String)] = List()

  private val tokenize = (v: String) => v.split(' ')
  private val tokenizeTuple = (v: (String, String)) => tokenize(v._1)
  private val calculateWords = (l: List[(String, String)]) => l.map(tokenizeTuple(_).length).sum

  def addExample(ex: String, cl: String): Unit = {
    examples = (ex, cl) :: examples
  }

  def dictionary: Set[String] = examples.map(tokenizeTuple).flatten.toSet

  def model = {
    val docsByClass = examples.groupBy(_._2)
    val lengths = docsByClass.view.mapValues(calculateWords).toMap
    val docCounts = docsByClass.view.mapValues(_.length).toMap
    val wordsCount = docsByClass.view.mapValues(_.map(tokenizeTuple).flatten.groupBy(x => x).mapValues(_.length).toMap).toMap

    new NaiveBayesModel(lengths, docCounts, wordsCount, dictionary.size)
  }

  def classifier = new NaiveBayesClassifier(model)
}
