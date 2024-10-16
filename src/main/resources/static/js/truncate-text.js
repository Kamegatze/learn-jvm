const truncateText = (tag) => {

    const sizeSign = parseInt(window.getComputedStyle(tag, null).getPropertyValue("font-size"))
    const countLine = parseInt(tag.dataset['countLine'])
    const width = tag.offsetWidth
    const signsInLine = width / sizeSign
    const allSign = signsInLine * countLine * 2
    return tag.textContent.length > allSign ? `${tag.textContent.slice(0, allSign)}...` : `${tag.textContent}...`
}