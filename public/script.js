document.getElementById('predictionForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const predictBtn = document.getElementById('predictBtn');
    const loading = document.getElementById('loading');
    const resultsContainer = document.getElementById('results');
    const noData = document.getElementById('noData');
    const tbody = document.querySelector('#resultsTable tbody');

    // UI Reset
    tbody.innerHTML = '';
    resultsContainer.classList.add('hidden');
    noData.classList.add('hidden');
    loading.classList.remove('hidden');
    predictBtn.disabled = true;

    // Gather data
    const formData = {
        rank: document.getElementById('rank').value,
        gender: document.getElementById('gender').value,
        category: document.getElementById('category').value,
        branch: document.getElementById('branch').value
    };

    try {
        const response = await fetch('/api/predict', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        if (!response.ok) throw new Error('Network response was not ok');

        const colleges = await response.json();

        loading.classList.add('hidden');
        predictBtn.disabled = false;

        if (colleges.length === 0) {
            noData.classList.remove('hidden');
            return;
        }

        colleges.forEach(college => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td><span style="font-weight:600; color:var(--primary-color)">${college.instCode}</span></td>
                <td>${college.collegeName}</td>
                <td><span style="background:#e0c3fc; padding: 4px 8px; border-radius:4px; font-size:0.9em">${college.branchCode}</span></td>
                <td>₹${college.fees.toLocaleString('en-IN')}</td>
            `;
            tbody.appendChild(row);
        });

        resultsContainer.classList.remove('hidden');

    } catch (error) {
        console.error('Error:', error);
        loading.classList.add('hidden');
        predictBtn.disabled = false;
        alert('An error occurred while fetching predictions.');
    }
});
