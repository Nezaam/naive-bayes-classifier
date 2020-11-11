package com.krymov.classifier

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class ClassifierSpec extends AnyFlatSpec {
  "Classifier" should "be able to guess classes" in {
    val c = new NaiveBayesLearningAlgorithm()
    c.addExample("предоставляю услуги бухгалтера", "SPAM")
    c.addExample("спешите купить виагру", "SPAM")
    c.addExample("надо купить молоко", "HAM")

    val bestClass = c.classifier.classify("надо купить сигареты")
    bestClass shouldEqual("HAM")
  }

}
