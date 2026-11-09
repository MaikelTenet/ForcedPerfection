//Mein Javascript, wurde sehr viel mit Chat-GPT und der hilfe von anderen Geschrieben!
const outputDiv = document.getElementById('output')
const form = document.getElementById('command-form')
const input = document.getElementById('command-input')

async function sendCommand(command) {

    const inputLine = document.createElement("div")
    inputLine.className = "line player-input"
    inputLine.textContent = command
    outputDiv.appendChild(inputLine)

    try {
        const response = await fetch("/game?aktion=" + encodeURIComponent(command))
        const text = await response.text()

        const block = document.createElement("div")
        block.className = "text-block game-output"
        outputDiv.appendChild(block)

        const lines = text.split("\n")

        for (const part of lines) {
            const line = document.createElement("div")
            line.className = "text-line"
            block.appendChild(line)

            await typeText(line, part, 1)
        }
        outputDiv.scrollTop = outputDiv.scrollHeight
    } catch (error) {
        const errorLine = document.createElement("div")
        errorLine.className = "line game-output"
        errorLine.textContent = "Fehler: " + error.message
        outputDiv.appendChild(errorLine)

        outputDiv.scrollTop = outputDiv.scrollHeight
    }
}

form.addEventListener('submit', async (e) => {
    e.preventDefault()
    const command = input.value
    input.value = ''
    await sendCommand(command)
})

async function typeText(element, text, speed = 10) {
    element.textContent = ""

    for (let char of text) {
        element.textContent += char
        outputDiv.scrollTop = outputDiv.scrollHeight

        await new Promise(resolve => setTimeout(resolve, speed))
    }
}

const startBlock = document.createElement("div")
startBlock.className = "text-block game-output"

const startLines = [
    "Schreibe 'Start' um das Spiel zu starten"
]

startLines.forEach(text => {
    const line = document.createElement("div")
    line.className = "text-line"
    line.textContent = text
    startBlock.appendChild(line)
})

outputDiv.appendChild(startBlock)
outputDiv.scrollTop = outputDiv.scrollHeight