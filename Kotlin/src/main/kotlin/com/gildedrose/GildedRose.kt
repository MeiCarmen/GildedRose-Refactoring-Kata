package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {
            updateSellIn(item)
            // make separate update quality funcs for exceptions
            updateQuality1(item)
            updateQuality2(item)
        }
    }

    fun updateSellIn(item: Item) {
        if (item.name != "Sulfuras, Hand of Ragnaros") {
            item.sellIn = item.sellIn - 1
        }
    }

    fun updateQuality1(item: Item) {
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            if (item.quality > 0) {
                if (item.name != "Sulfuras, Hand of Ragnaros") {
                    changeQuality(item, -1)
                }
            }
        } else {
            if (item.quality < 50) {
                changeQuality(item, 1)

                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            changeQuality(item, 1)
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            changeQuality(item, 1)
                        }
                    }
                }
            }
        }
    }

    fun updateQuality2(item: Item) {
        if (item.sellIn < 0) {
            if (item.name != "Aged Brie") {
                if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.quality > 0) {
                        if (item.name != "Sulfuras, Hand of Ragnaros") {
                            changeQuality(item, -1)
                        }
                    }
                } else {
                    item.quality = 0
                }
            } else {
                if (item.quality < 50) {
                    changeQuality(item, 1)
                }
            }
        }
    }

    fun changeQuality(item: Item, change: Int){
        item.quality = item.quality + change
    }


}

