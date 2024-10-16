const transferText = (tag) => {
    const transferTextInsert = (word, width, sizeSign) => {
        if (width >= (sizeSign / 1.65) * word.length) return word
        const firstPart = word.slice(0, width / (sizeSign / 1.65))
        const secondPart = word.slice(width / (sizeSign / 1.65))
        return transferTextInsert(firstPart, width, sizeSign) + "- " +
            transferTextInsert(secondPart,width, sizeSign)
    }

    const width = tag.offsetWidth
    const sizeSign = parseInt(window.getComputedStyle(tag, null).getPropertyValue("font-size"))
    return tag.textContent.split(" ")
        .map(word => transferTextInsert(word, width, sizeSign))
        .join(" ")
}