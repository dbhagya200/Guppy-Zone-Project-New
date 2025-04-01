// Save the original fetch function
const originalFetch = window.fetch;

// Override fetch to attach Authorization header
window.fetch = async (url, options = {}) => {
    const token = localStorage.getItem("authToken"); // Get stored token

    if (token) {
        options.headers = {
            ...options.headers, // Keep existing headers
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}` // Attach token to every request
        };
    }

    try {
        const response = await originalFetch(url, options);

        // Handle unauthorized responses globally
        if (response.status === 401) {
            alert("Session expired. Please log in again.");
            localStorage.removeItem("authToken"); // Clear token
            window.location.href = "/login.html"; // Redirect to login
        }

        return response; // Return the response
    } catch (error) {
        console.error("Fetch error:", error);
        throw error; // Re-throw error for specific handling
    }
};
