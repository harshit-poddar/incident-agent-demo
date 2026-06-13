package com.acme.payments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Looks up payments for a user. This is the deployed service code the incident
 * agent watches. It ships with a deliberate CWE-89 SQL injection so the
 * security-scan job fails -> the agent is triggered -> the fine-tuned vuln-fixer
 * model proposes the parameterised fix -> a PR is opened (after human approval).
 */
public class PaymentRepository {
    private final Connection connection;

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

    public ResultSet findByUser(String userId) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM payments WHERE user_id = ?");
        pstmt.setString(1, userId);
        return pstmt.executeQuery();
    }
}