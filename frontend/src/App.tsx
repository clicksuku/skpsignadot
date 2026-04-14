import { useState } from 'react'

interface CalculationRequest {
  num1: number
  num2: number
  operation: string
}

interface CalculationResponse {
  result: number
  operation: string
  num1: number
  num2: number
}

function App() {
  const [num1, setNum1] = useState<string>('')
  const [num2, setNum2] = useState<string>('')
  const [operation, setOperation] = useState<string>('ADD')
  const [result, setResult] = useState<number | null>(null)
  const [loading, setLoading] = useState<boolean>(false)
  const [error, setError] = useState<string>('')

  const operations = [
    { key: 'ADD', label: '+' },
    { key: 'SUBTRACT', label: '−' },
    { key: 'MULTIPLY', label: '×' },
    { key: 'DIVIDE', label: '÷' }
  ]

  const calculate = async () => {
    setError('')
    setResult(null)
    
    if (!num1 || !num2) {
      setError('Please enter both numbers')
      return
    }

    const n1 = parseFloat(num1)
    const n2 = parseFloat(num2)

    if (isNaN(n1) || isNaN(n2)) {
      setError('Please enter valid numbers')
      return
    }

    setLoading(true)

    try {
      const request: CalculationRequest = { num1: n1, num2: n2, operation }
      
      const response = await fetch('/api/calculate', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(request)
      })

      if (!response.ok) {
        const errorData = await response.json()
        throw new Error(errorData.error || 'Calculation failed')
      }

      const data: CalculationResponse = await response.json()
      setResult(data.result)
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An error occurred')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="calculator-container">
      <h1 className="calculator-title">Calculator</h1>
      
      <div className="input-group">
        <label htmlFor="num1">First Number</label>
        <input
          id="num1"
          type="number"
          value={num1}
          onChange={(e) => setNum1(e.target.value)}
          placeholder="Enter first number"
        />
      </div>

      <div className="operation-buttons">
        {operations.map((op) => (
          <button
            key={op.key}
            className={`operation-btn ${operation === op.key ? 'active' : ''}`}
            onClick={() => setOperation(op.key)}
          >
            {op.label}
          </button>
        ))}
      </div>

      <div className="input-group">
        <label htmlFor="num2">Second Number</label>
        <input
          id="num2"
          type="number"
          value={num2}
          onChange={(e) => setNum2(e.target.value)}
          placeholder="Enter second number"
        />
      </div>

      <button 
        className="calculate-btn" 
        onClick={calculate}
        disabled={loading}
      >
        {loading ? <span className="loading"></span> : 'Calculate'}
      </button>

      {error && <div className="error-message">{error}</div>}

      {result !== null && (
        <div className="result-container">
          <div className="result-label">Result</div>
          <div className="result-value">{result}</div>
        </div>
      )}
    </div>
  )
}

export default App
